<div
        class="modal fade bd-example-modal-lg"
        id="openUpdatePackageDetailsModal${tracking.mail.mailId}"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true">
  <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Update Package Details</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="card-body">
          <div class="form-body">
            <form method="POST" action="/update-package-details">
              <input type="hidden" name="mailId" value="${tracking.mail.mailId}">
              <div class="form-row" style="margin-top: 20px">
                <div class="col">
                  <label for="weight">Weight</label>
                  <input class="form-control select-filter" name="weight"
                         id="weight" required/>
                </div>
                <div class="col">
                  <label for="inputTypeofParcel">Select Type of Parcel</label>
                  <select name="parcelType" id="inputTypeofParcel" class="custom-select"
                               aria-label="Default select example" >
                    <option value="Small 20CM X 20CM">Small 20CM X 20CM</option>
                    <option value="Medium 45CM X 45CM">Medium 45CM X 45CM</option>
                    <option value="Large 80CM X 80CM">Large 80CM X 80CM</option>
                    <option value="Card Envelop">Card Envelop</option>
                    <option value="Express Delivery Flyer">Express Delivery Flyer</option>
                  </select>
                </div>
              </div>
              <div class="form-row" style="margin-top: 20px">
                <div class="col">
                  <label for="description">New Address</label>
                  <textarea class="form-control select-filter" name="description"
                            id="description" required></textarea>
                </div>
              </div>
              <hr/>
              <button class="btn btn-primary" type="submit" value="submit">
                Update
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>