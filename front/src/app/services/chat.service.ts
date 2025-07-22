import { Injectable } from '@angular/core';
import { Client, IMessage } from '@stomp/stompjs';
import { BehaviorSubject, Observable, of } from 'rxjs';
import SockJS from 'sockjs-client';
import { ChatMessageDto } from '../models/chat-message.dto';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private stompClient: Client;
  private chatId: number | null = null;
  private messageSubject = new BehaviorSubject<ChatMessageDto | null>(null);
  message$ = this.messageSubject.asObservable();

  constructor(private http: HttpClient) {
    this.stompClient = new Client({
      brokerURL: undefined,
      webSocketFactory: () => new SockJS(`http://localhost:8080/ws`),
      reconnectDelay: 5000,
    });
  }

  public connect(): void {
    const savedChatId = localStorage.getItem('chatId');
    if (savedChatId) {
      this.chatId = +savedChatId;
    }

    this.stompClient.onConnect = () => {
      const destination = this.chatId ? `/topic/support.${this.chatId}` : `/topic/support.*`;

      this.stompClient.subscribe(destination, (msg: IMessage) => {
        const message: ChatMessageDto = JSON.parse(msg.body);

        if (!this.chatId && message.chatId) {
          this.chatId = message.chatId;
          localStorage.setItem('chatId', this.chatId.toString());
        }

        this.messageSubject.next(message);
      });
    };

    this.stompClient.activate();
  }

  public sendMessage(content: string, senderId: number): void {
    const message: ChatMessageDto = {
      chatId: this.chatId,
      senderId,
      content,
      sendDate: new Date()
    };

    this.stompClient.publish({
      destination: '/app/chat.send',
      body: JSON.stringify(message),
    });
  }

  private resetChat(): void {
    this.chatId = null;
    localStorage.removeItem('chatId');
    this.messageSubject.next(null);
  }


  public endChatSession(): Observable<any> {
    if (!this.chatId) return of(null);
    const chatId = this.chatId;
    this.resetChat();
    return this.http.put(`/api/chat/end/${chatId}`, {});
  }
}
