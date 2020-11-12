// this class is used for the update
package com.socialeshop.api.core.payloads.inventory;

public class UploadResponse {
    private Long id;
    public UploadResponse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
