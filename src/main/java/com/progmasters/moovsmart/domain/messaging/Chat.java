package com.progmasters.moovsmart.domain.messaging;


import com.progmasters.moovsmart.domain.PropertyAdvert;
import com.progmasters.moovsmart.domain.user.User;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(
        columnNames = {"advert_id", "enquirer_id"})})
public class Chat extends Conversation {
    @OneToOne
    private PropertyAdvert advert;
    @OneToOne
    private User enquirer;

    @Entity
    @Table(uniqueConstraints = {@UniqueConstraint(
            columnNames = {"partner_id", "conversation_id"})})
    public static class View extends com.progmasters.moovsmart.domain.messaging.View<Chat> {
        @OneToOne
        private User partner;
        private Integer unread;

        public View() {
        }

        public View(User user, Chat chat) {
            super(user,chat);
            this.partner = user.getId().equals(chat.enquirer.getId())
                    ? chat.advert.getUser()
                    : chat.enquirer;
            this.unread = 0;
        }

        @Override
        public Chat getConversation() {
            return this.conversation;
        }

        public User getPartner() {
            return partner;
        }

        public String getTitle() {
            return this.conversation
                    .getAdvert()
                    .getTitle();
        }


        public View addUnread() {
            this.unread += 1;
            return this;
        }

        public View readAll() {
            this.unread = 0;
            return this;
        }

        public Integer getUnread() {
            return unread;
        }
    }

    public Chat() {
    }

    public Chat(User enquirer, PropertyAdvert advert) {
        this.enquirer = enquirer;
        this.advert = advert;
    }

    public PropertyAdvert getAdvert() {
        return advert;
    }
}
