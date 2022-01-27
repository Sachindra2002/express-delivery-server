<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div
        class="modal fade bd-example-modal-lg"
        id="openViewResponseDisputeModal${dispute.disputeId}"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Dispute ID : ${dispute.disputeId}</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="card-body">
                    <div class="form-row">
                        <div class="col">
                            <label for="response">Response</label>
                            <textarea class="form-control select-filter" name="response"
                                      id="response">${dispute.response}</textarea>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

