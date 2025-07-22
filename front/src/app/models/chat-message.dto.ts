export interface ChatMessageDto {
  chatId: number | null;
  senderId: number;
  content: string;
  sendDate: Date;
}
