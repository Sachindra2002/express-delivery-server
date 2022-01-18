<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div
        class="modal fade bd-example-modal-lg"
        id="openTransitpackageModal${mail.mailId}"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Transit Package</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="card-body">
                    <div class="form-body">

                        <div class="form-row">
                            <div class="col">
                                <label for="parcelId">Package ID</label>
                                <input style="text-align: center" class="form-control select-filter"
                                       id="parcelId" readonly value="${mail.mailId}">
                            </div>
                        </div>
                        <div class="form-row">
                            <form method="POST" action="/transit-package">
                                <input type="hidden" value="${mail.mailId}" name="mailId">
                                <div class="form-row">
                                    <div class="col">
                                        <label for="centerId">Select service center</label>
                                        <select name="centerId" id="centerId" class="custom-select"
                                                aria-label="Default select example">
                                            <c:forEach var="center" items="${center_list}">
                                                <option value="${center.centreId}">${center.center}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <hr/>
                                <button class="btn btn-primary" type="submit" value="submit">
                                    Transit Package
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>