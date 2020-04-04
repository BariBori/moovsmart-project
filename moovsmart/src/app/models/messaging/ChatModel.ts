import { MessageModel } from './MessageModel';

export interface ChatModel {
    id: number;
    messages: MessageModel[];
}
