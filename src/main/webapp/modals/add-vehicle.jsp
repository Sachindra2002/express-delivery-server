<div
        class="modal fade bd-example-modal-lg"
        id="openAddVehicleModal"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Add Vehicle</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="card-body">
                    <div class="form-body">
                        <form method="POST" action="/add-vehicle">
                            <div class="form-row">
                                <div class="col">
                                    <label for="vehicleType">Select Vehicle Type</label>
                                    <select name="vehicleType" id="vehicleType" class="custom-select"
                                            aria-label="Default select example" required>
                                        <option value="Lorry">Lorry</option>
                                        <option value="Van">Van</option>
                                        <option value="Motorbike">Motorbike</option>
                                    </select>
                                </div>
                                <div class="col">
                                    <div class="col">
                                        <label for="vehicleNumber">Vehicle Number</label>
                                        <input class="form-control select-filter" name="vehicleNumber"
                                                  id="vehicleNumber" required/>
                                    </div>
                                </div>
                            </div>
                            <hr/>
                            <button class="btn btn-primary" type="submit" value="submit">
                                Add Vehicle
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>