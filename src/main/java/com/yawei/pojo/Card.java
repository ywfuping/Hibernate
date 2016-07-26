package com.yawei.pojo;


public class Card {
    private Integer id;
    private String cardname;
    private Person person;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardname='" + cardname + '\'' +
                ", person=" + person +
                '}';
    }
}
