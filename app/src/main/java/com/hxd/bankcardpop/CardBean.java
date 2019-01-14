package com.hxd.bankcardpop;

/**
 * Content：
 * Actor：韩小呆 ヾ(✿ﾟ▽ﾟ)ノ
 * Time:  2019/01/05 21:52
 * Update:
 * Time:
 */
public class CardBean {

    private int Id;
    private String custId;
    private String card_username;
    private String card_bank;
    private String cardno;
    private String card_userphone;

    public CardBean(int id, String custId, String card_username, String card_bank, String cardno, String card_userphone) {
        Id = id;
        this.custId = custId;
        this.card_username = card_username;
        this.card_bank = card_bank;
        this.cardno = cardno;
        this.card_userphone = card_userphone;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCard_username() {
        return card_username;
    }

    public void setCard_username(String card_username) {
        this.card_username = card_username;
    }

    public String getCard_bank() {
        return card_bank;
    }

    public void setCard_bank(String card_bank) {
        this.card_bank = card_bank;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getCard_userphone() {
        return card_userphone;
    }

    public void setCard_userphone(String card_userphone) {
        this.card_userphone = card_userphone;
    }

    @Override
    public String toString() {
        return "BankCardBean{" +
                "Id='" + Id + '\'' +
                ", custId='" + custId + '\'' +
                ", card_username='" + card_username + '\'' +
                ", card_bank='" + card_bank + '\'' +
                ", cardno='" + cardno + '\'' +
                ", card_userphone='" + card_userphone + '\'' +
                '}';
    }
}
