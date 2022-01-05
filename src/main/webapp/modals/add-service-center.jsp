<div
        class="modal fade bd-example-modal-lg"
        id="openAddCenterModal"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true">
    <div class="modal-dialog modal-lg  modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Add Service Center</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="card-body">
                    <div class="form-body">
                        <form method="POST" action="/add-service-center">
                            <div class="form-row">
                                <div class="col">
                                    <label for="city">Select City</label>
                                    <select name="city" id="city" class="custom-select"
                                            aria-label="Default select example" required>
                                        <option value="Negombo">Negombo</option>
                                        <option value="Colombo">Colombo</option>
                                        <option value="Galle">Galle</option>
                                    </select>
                                </div>
                                <div class="col">
                                    <div class="col">
                                        <label for="center">Center Name</label>
                                        <input class="form-control select-filter" name="center"
                                               id="center" required/>
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="col">
                                        <label for="address">Center Address</label>
                                        <input class="form-control select-filter" name="address"
                                               id="address" required/>
                                    </div>
                                </div>
                            </div>
                            <hr/>
                            <button class="btn btn-primary" type="submit" value="submit">
                                Add Service Center
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>