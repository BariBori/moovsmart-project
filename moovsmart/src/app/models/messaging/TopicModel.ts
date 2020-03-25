import { Message } from '@angular/compiler/src/i18n/i18n_ast';

export interface TopicModel {
    advertId: number;
    advertTitle: string;
    advertiser: string;
    enquirer: string;
    messages: Message[];
}