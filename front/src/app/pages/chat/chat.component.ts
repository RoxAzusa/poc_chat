import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model';
import { ChatService } from '../../services/chat.service';

@Component({
  selector: 'app-chat',
  imports: [],
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent implements OnInit{
  userJson?: any;
  user?: User;

  constructor( private chatService: ChatService) {}

  ngOnInit(): void {
    this.userJson = localStorage.getItem('user');
    this.user = this.userJson ? JSON.parse(this.userJson) : null;
  }
}
