<div
        class="modal fade bd-example-modal-lg"
        id="openPackageAgentModal${mail.mailId}"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Package ID : ${mail.mailId}</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="card-body">
                    <div class="form-row">
                        <div class="col">
                            <label for="senderEmail">Sender Email</label>
                            <input class="form-control select-filter"
                                   id="senderEmail" readonly value="${mail.user.getEmail()}">
                        </div>
                        <div class="col">
                            <label for="senderName">Sender Name</label>
                            <input class="form-control select-filter"
                                   id="senderName" readonly
                                   value="${mail.user.getFirstName()} ${mail.user.getLastName()}">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <label for="receiverEmail">Receiver Email</label>
                            <input class="form-control select-filter"
                                   id="receiverEmail" readonly value="${mail.receiverEmail}">
                        </div>
                        <div class="col">
                            <label for="receiverName">Receiver Name</label>
                            <input class="form-control select-filter"
                                   id="receiverName" readonly
                                   value="${mail.receiverFirstName} ${mail.receiverLastName}">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <label for="pAddress">Pickup Address</label>
                            <textarea class="form-control select-filter"
                                      id="pAddress" readonly>${mail.pickupAddress}</textarea>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <label for="dAddress">Receiver Name</label>
                            <textarea class="form-control select-filter"
                                      id="dAddress" readonly>${mail.receiverAddress}</textarea>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <label for="desc">Package Description</label>
                            <textarea class="form-control select-filter"
                                      id="desc" readonly>${mail.description}</textarea>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <label for="type">Parcel Type</label>
                            <input style="text-align: center" class="form-control select-filter"
                                   id="type" readonly value="${mail.parcelType}">
                        </div>
                        <div class="col">
                            <label for="weight">Weight</label>
                            <input style="text-align: center" class="form-control select-filter"
                                   id="weight" readonly
                                   value="${mail.weight}KG">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <label for="date">Preferred pickup Date</label>
                            <input class="form-control select-filter"
                                   id="date" readonly value="${mail.date}">
                        </div>
                        <div class="col">
                            <label for="time">Preferred pickup Time</label>
                            <input class="form-control select-filter"
                                   id="time" readonly
                                   value="${mail.time}">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <label for="cost">Total Cost</label>
                            <input class="form-control select-filter"
                                   id="cost" readonly value="${mail.totalCost}">
                        </div>
                        <div class="col">
                            <label for="requested">Requested on</label>
                            <input class="form-control select-filter"
                                   id="requested" readonly
                                   value="${mail.createdAt}">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>