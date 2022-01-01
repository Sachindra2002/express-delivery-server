<div
        class="modal fade bd-example-modal-lg"
        id="openResponseInquiryModal${inquiry.inquiryId}"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Respond</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="card-body">
                    <div class="form-body">
                        <form method="POST" action="/send-response-inquiry">
                            <div class="form-row">
                                <div class="col">
                                    <label for="inquiryId">Inquiry ID</label>
                                    <input class="form-control select-filter" value="${inquiry.inquiryId}" name="inquiryId"
                                           id="inquiryId" required readonly/>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col">
                                    <label for="response">Response</label>
                                    <textarea class="form-control select-filter" name="response"
                                              id="response" required></textarea>
                                </div>
                            </div><hr/>
                            <button class="btn btn-primary" type="submit" value="submit">
                                Send Response
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>