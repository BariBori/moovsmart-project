package com.progmasters.moovsmart.domain.messaging;


import com.progmasters.moovsmart.domain.PropertyAdvert;
import com.progmasters.moovsmart.domain.user.User;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(uniqueConstraints =
        {@UniqueConstraint(columnNames = {"advert", "enquirer"})})
@Entity
public class Chat extends Conversation {
    @OneToOne
    private PropertyAdvert advert;
    @OneToOne
    private User enquirer;

    @Entity
    public static class View extends com.progmasters.moovsmart.domain.messaging.View<Chat> {
        @OneToOne
        private User partner;
        private Boolean unread;

        public View() {
        }

        public View(User user, Chat chat) {
            super(user, chat);
            this.partner = user.getId().equals(chat.enquirer.getId())
                    ? chat.advert.getUser()
                    : chat.enquirer;
            this.unread = true;
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

        public Boolean hasUnread() {
            return unread;
        }

        public View unRead() {
            this.unread = true;
            return this;
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

    public User getEnquirer() {
        return enquirer;
    }
}
