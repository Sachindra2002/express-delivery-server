<div
        class="modal fade bd-example-modal-lg"
        id="openInquiryModal"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Add Inquiry</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="card-body">
                    <div class="form-body">
                        <form method="POST" action="/add-inquiry">
                            <div class="form-row">
                                <div class="col">
                                    <label for="inquiryType">Select Inquiry Type</label>
                                    <select name="inquiryType" id="inquiryType" class="custom-select" aria-label="Default select example" required>
                                        <option value="Faulty Product">Faulty Product</option>
                                        <option value="Did not arrive">Did not arrive</option>
                                        <option value="Wrong Product">Wrong Product</option>
                                        <option value="Other">Other (Please Specify)</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col">
                                    <label for="description">Description</label>
                                    <textarea class="form-control select-filter" name="description"
                                              id="description" required></textarea>
                                </div>
                            </div><hr/>
                            <button class="btn btn-primary" type="submit" value="submit">
                                Add Inquiry
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>