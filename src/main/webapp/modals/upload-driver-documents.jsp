<div
        class="modal fade bd-example-modal-lg"
        id="openUploadDocumentsModal${driver.driverId}"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Upload Documents</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="card-body">
                    <div class="form-body">
                        <form method="POST" action="/upload-documents" enctype="multipart/form-data">
                            <input type="hidden" name="driverId" value="${driver.driverId}">
                            <div class="form-row" style="margin-top: 20px">
                                <div class="col" style="margin-right: 10px">
                                    <input type="file" class="custom-file-input" name="nicImage" id="nicImage" required>
                                    <label class="custom-file-label" for="nicImage">Upload NIC</label>
                                </div>
                                <div class="col" style="margin-right: 10px">
                                    <input type="file" class="custom-file-input" name="licence" id="licence" required>
                                    <label class="custom-file-label" for="licence">Upload Licence</label>
                                </div>
                                <div class="col">
                                    <input type="file" class="custom-file-input" name="insurance" id="insurance" required>
                                    <label class="custom-file-label" for="insurance">Upload Insurance</label>
                                </div>
                            </div>
                            <hr/>
                            <button class="btn btn-primary" type="submit" value="submit">
                                Upload Documents
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>