<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div
        class="modal fade bd-example-modal-lg"
        id="openViewAgentModal${agent.phoneNumber}"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Agent Email : ${agent.email}</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="card-body">
                    <div class="form-row">
                        <div class="col">
                            <label for="senderEmail">Agent Name</label>
                            <input class="form-control select-filter"
                                   id="senderEmail" readonly value="${agent.firstName} ${agent.lastName}">
                        </div>
                        <div class="col">
                            <label for="senderName">Agent Contact</label>
                            <input class="form-control select-filter"
                                   id="senderName" readonly
                                   value="${agent.phoneNumber}">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <label for="receiverEmail">Agent City</label>
                            <input class="form-control select-filter"
                                   id="receiverEmail" readonly
                                   value="${agent.location}">
                        </div>
                        <div class="col">
                            <label for="receiverName">Agent Center</label>
                            <input class="form-control select-filter"
                                   id="receiverName" readonly
                                   value="${agent.serviceCentre.center}">
                        </div>
                    </div>
                    <hr/>
                    <form style="float: left" method="post" action="/delete-agent">
                        <input type="hidden" value="${agent.email}" name="email">
                        <button class="btn btn-danger" type="submit">
                            Remove Agent
                        </button>
                    </form>
                    <button style="margin-left: 20px" data-toggle="modal" data-target="#openUpdateCenterModal${agent.phoneNumber}" class="btn btn-primary" type="submit">
                        Change Service Center
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

