<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div
        class="modal fade bd-example-modal-lg"
        id="openEditPackageDetailsModal${tracking.mail.mailId}"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Update Package details</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="card-body">
                    <div class="form-body">
                        <button class="btn btn-primary" data-toggle="modal" data-target="#openUpdatePhoneAndAddressModal${tracking.mail.mailId}" type="submit" value="submit">
                            Edit Receiver Details
                        </button>
                        <button class="btn btn-primary" data-toggle="modal" data-target="#openUpdatePackageDetailsModal${tracking.mail.mailId}" type="submit" value="submit">
                            Edit Package Details
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>