<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav style="display: flex; justify-content: space-around; align-items: center; min-height: 8vh; background-color: #febc17; font-family: 'Poppins', sans-serif">
    <sec:authorize access="hasRole('CUSTOMER')">
        <div style="color: white; text-transform: uppercase; letter-spacing: 3px; font-size: 20px; margin-top: 5px"
             class="logo">
            <a style="color: white; text-decoration: none" href="/"><h4>Express Delivery</h4></a>
        </div>
        <ul style="display: flex; width: 18%; justify-content: space-around; margin-top: 10px">
            <li style="list-style: none">
                <div class="dropdown">
                    <div style="color: black; text-decoration: none; letter-spacing: 1px; font-weight: bold; font-size: 17px"
                         class="dropbtn">Account <i class="fa fa-arrow-down" aria-hidden="true"></i>
                    </div>
                    <div class="dropdown-content">
                        <a href="/inquiries">Inquiries</a>
                        <a href="/disputes">Disputes</a>
                        <a class="btn btn-outline-secondary my-2 my-sm-0 logout-btn settings-btn" data-toggle="modal"
                           data-target="#settingsModal">Settings</a>
                        <a style="color: red" href="/logout">Log out</a>
                    </div>
                </div>
            </li>
        </ul>
    </sec:authorize>
    <sec:authorize access="hasRole('ADMIN')">
        <div style="color: white; text-transform: uppercase; letter-spacing: 3px; font-size: 20px; margin-top: 5px"
             class="logo">
            <a style="color: white; text-decoration: none" href="/"><h4>Express Delivery</h4></a>
        </div>
        <ul style="display: flex; width: 50%; justify-content: space-around; margin-top: 10px">
            <li style="list-style: none">
                <a style="color: black; text-decoration: none; letter-spacing: 1px; font-weight: bold; font-size: 17px"
                   href="/all-shipments">Shipments</a>
            </li>
            <li style="list-style: none">
                <a style="color: black; text-decoration: none; letter-spacing: 1px; font-weight: bold; font-size: 17px"
                   href="/service-centers">Service Centers</a>
            </li>
            <li style="list-style: none">
                <a style="color: black; text-decoration: none; letter-spacing: 1px; font-weight: bold; font-size: 17px"
                   href="/vehicles">Vehicles</a>
            </li>
            <li style="list-style: none">
                <div class="dropdown">
                    <div style="color: black; text-decoration: none; letter-spacing: 1px; font-weight: bold; font-size: 17px"
                         class="dropbtn">Complaints <i class="fa fa-arrow-down" aria-hidden="true"></i>
                    </div>
                    <div class="dropdown-content">
                        <a href="/view-disputes">Disputes</a>
                        <a href="/view-inquiries">Inquiries</a>
                    </div>
                </div>
            </li>
            <li style="list-style: none">
                <div class="dropdown">
                    <div style="color: black; text-decoration: none; letter-spacing: 1px; font-weight: bold; font-size: 17px"
                         class="dropbtn">Users <i class="fa fa-arrow-down" aria-hidden="true"></i>
                    </div>
                    <div class="dropdown-content">
                        <a href="/agents">Agents</a>
                        <a href="/drivers">Drivers</a>
                        <a href="/customers">Customers</a>
                    </div>
                </div>
            </li>
            <li style="list-style: none">
                <div class="dropdown">
                    <div style="color: black; text-decoration: none; letter-spacing: 1px; font-weight: bold; font-size: 17px"
                         class="dropbtn">Account <i class="fa fa-arrow-down" aria-hidden="true"></i>
                    </div>
                    <div class="dropdown-content">
                        <a class="btn btn-outline-secondary my-2 my-sm-0 logout-btn settings-btn" data-toggle="modal"
                           data-target="#settingsModal">Settings</a>
                        <a style="color: red" href="/logout">Log out</a>
                    </div>
                </div>
            </li>
        </ul>
    </sec:authorize>
    <sec:authorize access="hasRole('AGENT')">
        <div style="color: white; text-transform: uppercase; letter-spacing: 3px; font-size: 20px; margin-top: 5px"
             class="logo">
            <a style="color: white; text-decoration: none" href="/"><h4>Express Delivery</h4></a>
        </div>
        <ul style="display: flex; width: 25%; justify-content: space-around; margin-top: 10px">
            <li style="list-style: none">
                <div class="dropdown">
                    <div style="color: black; text-decoration: none; letter-spacing: 1px; font-weight: bold; font-size: 17px"
                         class="dropbtn">Shipments <i class="fa fa-arrow-down" aria-hidden="true"></i>
                    </div>
                    <div class="dropdown-content">
                        <a href="/new-shipments-center">New Shipments</a>
                        <a href="/accepted-shipments-center">Assigned</a>
                        <a href="/transit-shipments-center">In transit</a>
                    </div>
                </div>
            </li>
            <li style="list-style: none">
                <a style="color: black; text-decoration: none; letter-spacing: 1px; font-weight: bold; font-size: 17px"
                   href="/center-drivers">Drivers</a>
            </li>
            <li style="list-style: none">
                <div class="dropdown">
                    <div style="color: black; text-decoration: none; letter-spacing: 1px; font-weight: bold; font-size: 17px"
                         class="dropbtn">Account <i class="fa fa-arrow-down" aria-hidden="true"></i>
                    </div>
                    <div class="dropdown-content">
                        <a class="btn btn-outline-secondary my-2 my-sm-0 logout-btn settings-btn" data-toggle="modal"
                           data-target="#settingsModal">Settings</a>
                        <a style="color: red" href="/logout">Log out</a>
                    </div>
                </div>
            </li>
        </ul>
    </sec:authorize>
    <sec:authorize access="hasRole('DRIVER')">
        <div style="color: white; text-transform: uppercase; letter-spacing: 3px; font-size: 20px; margin-top: 5px"
             class="logo">
            <a style="color: white; text-decoration: none" href="/"><h4>Express Delivery</h4></a>
        </div>
        <ul style="display: flex; width: 20%; justify-content: space-around; margin-top: 10px">
            <li style="list-style: none">
                <a style="color: black; text-decoration: none; letter-spacing: 1px; font-weight: bold; font-size: 17px"
                   href="/past-shipments">Past Shipments</a>
            </li>
            <li style="list-style: none">
                <div class="dropdown">
                    <div style="color: black; text-decoration: none; letter-spacing: 1px; font-weight: bold; font-size: 17px"
                         class="dropbtn">Account <i class="fa fa-arrow-down" aria-hidden="true"></i>
                    </div>
                    <div class="dropdown-content">
                        <a class="btn btn-outline-secondary my-2 my-sm-0 logout-btn settings-btn" data-toggle="modal"
                           data-target="#settingsModal">Settings</a>
                        <a style="color: red" href="/logout">Log out</a>
                    </div>
                </div>
            </li>
        </ul>
    </sec:authorize>
</nav>

<%@ include file="../modals/settings-modal.jsp" %>