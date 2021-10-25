<div
        class="modal fade"
        id="openCancelModal"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Cancel Package</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="card-body">
                    <div class="form-body">
                        <form method="POST" action="/cancel-parcel">
                            <div class="form-row">
                                <div class="col">
                                    <label for="mailId3">Package ID</label>
                                    <input style="text-align: center" class="form-control select-filter" name="mailId"
                                           id="mailId3" required readonly/>
                                </div>
                            </div>
                            <div style="text-align: center; margin-top: 10px"><img style="width: 20%" alt="warning" src="images/warning.jpg"/></div>
                            <div style="text-align: center; margin: 20px; font-weight: bold; font-size: 20px">Are you sure you want to cancel this package?!</div>
                            <div class="form-row">
                            </div>
                            <hr/>
                            <button class="btn btn-warning" type="submit" value="submit">
                                Cancel Package
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>