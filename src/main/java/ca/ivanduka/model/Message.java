package ca.ivanduka.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Message {
    private UUID messageID;
    private User author;
    private String content;
    private LocalDateTime createdTime;
    private LocalDateTime editedTime;
    private Message parentMessage;
    private MessageStatuses messageStatus;
    private List<Message> responses;

    // Creating a new Message
    public Message(User author, String content, Message parentMessage) {
        this.author = author;
        this.content = content;
        this.parentMessage = parentMessage;
        this.messageID = UUID.randomUUID();
        this.createdTime = LocalDateTime.now();
        this.editedTime = LocalDateTime.now();
        this.responses = new ArrayList<>();
        this.messageStatus = MessageStatuses.ADDED;
    }

    // Message from a DB
    public Message(UUID messageID, User author, String content, LocalDateTime createdTime, LocalDateTime editedTime,
                   Message parentMessage, List<Message> responses, MessageStatuses messageStatus) {
        this.messageID = messageID;
        this.author = author;
        this.content = content;
        this.createdTime = createdTime;
        this.editedTime = editedTime;
        this.parentMessage = parentMessage;
        this.responses = responses;
        this.messageStatus = messageStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return messageID.equals(message.messageID);
    }

    @Override
    public int hashCode() {
        return messageID.hashCode();
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageID=" + messageID +
                ", author=" + author +
                ", content='" + content + '\'' +
                ", createdTime=" + createdTime +
                ", editedTime=" + editedTime +
                ", parentMessage=" + parentMessage +
                ", responses=" + responses +
                '}';
    }

    public UUID getMessageID() {
        return messageID;
    }

    public MessageStatuses getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(MessageStatuses messageStatus) {
        boolean wantToSetDeleted = messageStatus.equals(MessageStatuses.DELETED);

        // ADDED -> to ADDED || EDITED || DELETED
        // EDITED -> to DELETED
        // DELETED -> cannot change
        if (this.isAdded() || wantToSetDeleted) {
            this.messageStatus = messageStatus;
        }

        if (this.isDeleted()) {
            this.content = "[THE MESSAGE IS DELETED]";
        }
    }

    private boolean isAdded() {
        return this.messageStatus.equals(MessageStatuses.ADDED);
    }

    private boolean isEdited() {
        return this.messageStatus.equals(MessageStatuses.EDITED);
    }

    private boolean isDeleted() {
        return this.messageStatus.equals(MessageStatuses.DELETED);
    }

    public User getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (this.isAdded()) {
            this.content = content;
            this.messageStatus = MessageStatuses.EDITED;
        }

        if (this.isEdited()) {
            this.editedTime = LocalDateTime.now();
        }

        // DO NOTHING IF MESSAGE IS DELETED
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public LocalDateTime getEditedTime() {
        return editedTime;
    }

    public Message getParentMessage() {
        return parentMessage;
    }

    public List<Message> getResponses() {
        return responses;
    }
}
