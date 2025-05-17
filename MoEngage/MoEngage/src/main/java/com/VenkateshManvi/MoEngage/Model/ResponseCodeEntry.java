package com.VenkateshManvi.MoEngage.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "responsecode_entry")
public class ResponseCodeEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String code;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "list_id")
    private ResponseCodeList responseCodeList;

    public ResponseCodeEntry() {
    }

    public ResponseCodeEntry(String code, String imageUrl, ResponseCodeList responseCodeList) {
        this.code = code;
        this.imageUrl = imageUrl;
        this.responseCodeList = responseCodeList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ResponseCodeList getResponseCodeList() {
        return responseCodeList;
    }

    public void setResponseCodeList(ResponseCodeList responseCodeList) {
        this.responseCodeList = responseCodeList;
    }

    @Override
    public String toString() {
        return "ResponseCodeEntry{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", responseCodeList=" + responseCodeList +
                '}';
    }
}
