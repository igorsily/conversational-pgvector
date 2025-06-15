package com.br.winlogic.conversationalpgvector.adapter.out.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "chat_cost")
public class ChatCostEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prompt_tokens", nullable = false, updatable = false)
    private int promptTokens;

    @Column(name = "completion_tokens", nullable = false, updatable = false)
    private int completionTokens;

    @Column(name = "total_tokens", nullable = false, updatable = false)
    private int totalTokens;

    @Column(name = "cost_input", nullable = false, updatable = false)
    private double costInput;

    @Column(name = "cost_output", nullable = false, updatable = false)
    private double costOutput;

    @Column(name = "cost_total", nullable = false, updatable = false)
    private double costTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPromptTokens() {
        return promptTokens;
    }

    public void setPromptTokens(int promptTokens) {
        this.promptTokens = promptTokens;
    }

    public int getCompletionTokens() {
        return completionTokens;
    }

    public void setCompletionTokens(int completionTokens) {
        this.completionTokens = completionTokens;
    }

    public int getTotalTokens() {
        return totalTokens;
    }

    public void setTotalTokens(int totalTokens) {
        this.totalTokens = totalTokens;
    }

    public double getCostInput() {
        return costInput;
    }

    public void setCostInput(double costInput) {
        this.costInput = costInput;
    }

    public double getCostOutput() {
        return costOutput;
    }

    public void setCostOutput(double costOutput) {
        this.costOutput = costOutput;
    }

    public double getCostTotal() {
        return costTotal;
    }

    public void setCostTotal(double costTotal) {
        this.costTotal = costTotal;
    }
}
