<div
        class="modal fade bd-example-modal-lg"
        id="openReturnModal"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Return Package</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="card-body">
                    <div class="form-body">
                        <form method="POST" action="/initiate-return">
                            <div class="form-row">
                                <div class="col">
                                    <label for="mailId2">Package ID</label>
                                    <input class="form-control select-filter" name="mailId"
                                           id="mailId2" required readonly/>
                                </div>
                                <div class="col">
                                    <label for="trackId">Tracking ID</label>
                                    <input class="form-control select-filter" name="trackingId"
                                           id="trackId" value="43" required readonly/>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col">
                                    <label for="returnType">Select Method of Return</label>
                                    <select name="returnType" id="returnType" class="custom-select" aria-label="Default select example" required>
                                        <option value="card">Pick Up</option>
                                        <option value="cash">Drop Off</option>
                                    </select>
                                </div>
                                <div class="col">
                                    <label for="reason">Select Reason to Return Product</label>
                                    <select name="reason" id="reason" class="custom-select" aria-label="Default select example" required>
                                        <option value="card">Faulty Product</option>
                                        <option value="cash">Wrong Product</option>
                                        <option value="cash">Other (please describe)</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="col">
                                    <label for="inputDate">Preferred Date to Pickup/Drop Off</label>
                                    <input type="date" class="form-control select-filter" name="date"
                                           id="inputDate" required>
                                </div>
                                <div class="col">
                                    <label for="inputTime">Preferred Time to Pickup/Drop Off</label>
                                    <input type="time" class="form-control select-filter" name="time"
                                           id="inputTime" required>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col">
                                    <label for="description">Description</label>
                                    <textarea class="form-control select-filter" name="description"
                                              id="description" required></textarea>
                                </div>
                            </div><hr/>
                            <div style="text-align: center"><p>*Note - Pack your parcel securely, with the product in the original and undamaged manufacturer's packaging along with any accessories, manuals, free items, etc. as delivered.</p></div><hr/>
                            <button class="btn btn-primary" type="submit" value="submit">
                                Return Item
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>