<div
        class="modal fade bd-example-modal-lg"
        id="openUpdateStatusModal${driver.driverId}"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Update Driver Status</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="card-body">
                    <div class="form-body">
                        <form method="POST" action="/update-driver-status">
                            <input type="hidden" name="driverId" value="${driver.driverId}">
                            <div class="form-row" style="margin-top: 20px">
                                <div class="col">
                                    <label for="status">Select Status</label>
                                    <select name="status" id="status" class="custom-select"
                                            aria-label="Default select example" required>
                                        <option value="Available">Available</option>
                                        <option value="Unavailable">Unavailable</option>
                                    </select>
                                </div>
                            </div>
                            <hr/>
                            <button class="btn btn-primary" type="submit" value="submit">
                                Update Status
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>