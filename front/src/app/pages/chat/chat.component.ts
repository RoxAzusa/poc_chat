import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model';
import { ChatService } from '../../services/chat.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ChatMessageDto } from '../../models/chat-message.dto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-chat',
  imports: [
    CommonModule,
    FormsModule],
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent implements OnInit {
  currentUser!: User;
  messages: ChatMessageDto[] = [];
  newMessage: string = '';

  constructor(
    private chatService: ChatService,
    private router: Router
  ) { }

  ngOnInit(): void {
    const userData = localStorage.getItem('user');
    if (userData) {
      this.currentUser = JSON.parse(userData);
    }

    this.chatService.connect();

    this.chatService.message$.subscribe((message) => {
      if (message) {
        this.messages.push(message);
      }
    })
  }

  sendMessage(): void {
    if (!this.newMessage.trim()) return;
    this.chatService.sendMessage(this.newMessage, this.currentUser.id);
    this.newMessage = '';
  }

  onLogoutClick(): void {
    const confirmLogout = window.confirm("Êtes-vous sûr de vouloir mettre fin à la conversation ?");
    if (confirmLogout) {
      this.chatService.endChatSession().subscribe(() => { });
      this.router.navigate(['/login']);
    }
  }
}
