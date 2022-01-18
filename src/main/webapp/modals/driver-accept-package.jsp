<div
        class="modal fade"
        id="openAcceptPackageDriverModal${mail.mailId}"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Accept Package</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="card-body">
                    <div class="form-body">
                        <form method="POST" action="/accept-parcel-driver">
                            <div class="form-row">
                                <div class="col">
                                    <label for="mailId3">Package ID</label>
                                    <input style="text-align: center" value="${mail.mailId}" class="form-control select-filter" name="mailId"
                                           id="mailId3" required readonly/>
                                </div>
                            </div>
                            <div style="text-align: center; margin-top: 10px"><img style="width: 20%" alt="warning" src="images/tick.jpg"/></div>
                            <div style="text-align: center; margin: 20px; font-weight: bold; font-size: 20px">Are you sure you want to Accept this package?!</div>
                            <div class="form-row">
                            </div>
                            <hr/>
                            <button class="btn btn-success" type="submit" value="submit">
                                Accept Package
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>