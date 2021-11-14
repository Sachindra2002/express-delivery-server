
<div
        class="modal fade bd-example-modal-lg"
        id="openAddDriverModal"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Add Driver</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="card-body">
                    <div class="form-body">
                        <form method="POST" action="/add-driver">
                            <div class="form-row">
                                <div class="col">
                                    <label for="firstName">Driver First Name</label>
                                    <input class="form-control select-filter" name="firstName"
                                           id="firstName" required/>
                                </div>
                                <div class="col">
                                    <label for="lastName">Driver Last Name</label>
                                    <input class="form-control select-filter" name="lastName"
                                           id="lastName" required/>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col">
                                    <label for="email">Driver Email</label>
                                    <input class="form-control select-filter" name="email"
                                           id="email" required/>
                                </div>
                                <div class="col">
                                    <label for="phone">Driver Phone Number</label>
                                    <input class="form-control select-filter" name="phone"
                                           id="phone" required/>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="col">
                                    <label for="location">City of Driver</label>
                                    <select name="location" id="location" class="custom-select"
                                            aria-label="Default select example" required>
                                        <option value="Colombo">Colombo</option>
                                        <option value="Negombo">Negombo</option>
                                        <option value="Negombo">Galle</option>
                                    </select>
                                </div>
                                <div class="col">
                                    <label for="center">Assign Service Center</label>
                                    <select name="center" id="center" class="custom-select"
                                            aria-label="Default select example" required>
                                        <c:forEach var="center" items="${centers}">
                                            <option value="${center.getCenter()}" >${center.getCenter()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col">
                                    <label for="dob">Driver Date of Birth</label>
                                    <input type="date" class="form-control select-filter" name="dob"
                                           id="dob" required/>
                                </div>
                                <div class="col">
                                    <label for="nic">Driver National Identity Number</label>
                                    <input class="form-control select-filter" name="nic"
                                           id="nic" maxlength="12" required/>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col">
                                    <label for="address">Driver Address</label>
                                    <textarea class="form-control select-filter" name="address"
                                              id="address" required></textarea>
                                </div>
                            </div>
                            <div class="form-row" style="margin-top: 20px">
                                <div class="col" style="margin-right: 10px">
                                    <input type="file" class="custom-file-input" name="nicImage" id="nicImage">
                                    <label class="custom-file-label" for="nicImage">Upload NIC</label>
                                </div>
                                <div class="col" style="margin-right: 10px">
                                    <input type="file" class="custom-file-input" name="licence" id="licence">
                                    <label class="custom-file-label" for="licence">Upload Licence</label>
                                </div>
                                <div class="col">
                                    <input type="file" class="custom-file-input" name="insurance" id="insurance">
                                    <label class="custom-file-label" for="insurance">Upload Insurance</label>
                                </div>
                            </div>
                            <hr/>
                            <div style="text-align: center"><p>*Note - Drivers' default password would be the drivers' email.</p></div>
                            <hr/>
                            <button class="btn btn-primary" type="submit" value="submit">
                                Add Driver
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
