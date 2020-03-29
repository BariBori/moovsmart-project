import { MessageModel } from './MessageModel';

export interface ChatModel {
    advertId: number;
    title: string;
    messages: MessageModel[];
}
