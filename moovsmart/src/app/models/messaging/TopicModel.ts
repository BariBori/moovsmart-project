import { MessageModel } from './MessageModel';

export interface TopicModel {
    advertId: number;
    advertTitle: string;
    advertiser: string;
    enquirer: string;
    messages: MessageModel[];
}
