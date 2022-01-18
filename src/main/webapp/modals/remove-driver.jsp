<div
        class="modal fade"
        id="openRemoveDriverModal${driver.user.phoneNumber}"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Remove Driver</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="card-body">
                    <div class="form-body">
                        <form method="POST" action="/remove-driver">
                            <div class="form-row">
                                <div class="col">
                                    <label for="email">Driver Email</label>
                                    <input style="text-align: center" value="${driver.user.email}" class="form-control select-filter" name="email"
                                           id="email" required readonly/>
                                </div>
                            </div>
                            <div style="text-align: center; margin-top: 10px"><img style="width: 20%" alt="warning" src="images/cross.jpg"/></div>
                            <div style="text-align: center; margin: 20px; font-weight: bold; font-size: 20px">Are you sure you want to delete the driver</div>
                            <div class="form-row">
                            </div>
                            <hr/>
                            <button class="btn btn-danger" type="submit" value="submit">
                                Remove driver
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>