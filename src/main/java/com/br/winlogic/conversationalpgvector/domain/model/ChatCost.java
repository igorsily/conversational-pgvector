package com.br.winlogic.conversationalpgvector.domain.model;

public class ChatCost {

    private final int promptTokens;
    private final int completionTokens;
    private final int totalTokens;

    private ChatCost(Builder builder) {
        this.promptTokens = builder.promptTokens;
        this.completionTokens = builder.completionTokens;
        this.totalTokens = builder.totalTokens;
    }

    public int getPromptTokens() {
        return promptTokens;
    }

    public int getCompletionTokens() {
        return completionTokens;
    }

    public int getTotalTokens() {
        return totalTokens;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int promptTokens;
        private int completionTokens;
        private int totalTokens;

        public Builder promptTokens(int promptTokens) {
            this.promptTokens = promptTokens;
            return this;
        }

        public Builder completionTokens(int completionTokens) {
            this.completionTokens = completionTokens;
            return this;
        }

        public Builder totalTokens(int totalTokens) {
            this.totalTokens = totalTokens;
            return this;
        }

        public ChatCost build() {
            return new ChatCost(this);
        }
    }
}
