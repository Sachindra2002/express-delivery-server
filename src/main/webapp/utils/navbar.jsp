<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav style="display: flex; justify-content: space-around; align-items: center; min-height: 8vh; background-color: #febc17; font-family: 'Poppins', sans-serif">
    <sec:authorize access="hasRole('CUSTOMER')">
        <div style="color: white; text-transform: uppercase; letter-spacing: 3px; font-size: 20px; margin-top: 5px"
             class="logo">
            <a style="color: white; text-decoration: none" href="/"><h4>Express Delivery</h4></a>
        </div>
        <ul style="display: flex; width: 30%; justify-content: space-around; margin-top: 10px">
            <li style="list-style: none">
                <a style="color: black; text-decoration: none; letter-spacing: 1px; font-weight: bold; font-size: 17px"
                   href="#">From Me</a>
            </li>
            <li style="list-style: none">
                <a style="color: black; text-decoration: none; letter-spacing: 1px; font-weight: bold; font-size: 17px"
                   href="#">To Me</a>
            </li>
            <li style="list-style: none">
                <a style="color: black; text-decoration: none; letter-spacing: 1px; font-weight: bold; font-size: 17px"
                   href="#">Completed</a>
            </li>
            <li style="list-style: none">
                <div class="dropdown">
                    <div style="color: black; text-decoration: none; letter-spacing: 1px; font-weight: bold; font-size: 17px"
                         class="dropbtn">Account <i class="fa fa-arrow-down" aria-hidden="true"></i>
                    </div>
                    <div class="dropdown-content">
                        <a href="#">Settings</a>
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
        <ul style="display: flex; width: 40%; justify-content: space-around; margin-top: 10px">
            <li style="list-style: none">
                <div class="dropdown">
                    <div style="color: black; text-decoration: none; letter-spacing: 1px; font-weight: bold; font-size: 17px"
                         class="dropbtn">Packages <i class="fa fa-arrow-down" aria-hidden="true"></i>
                    </div>
                    <div class="dropdown-content">
                        <a href="#">Ongoing</a>
                        <a href="#">Completed</a>
                    </div>
                </div>
            </li>
            <li style="list-style: none">
                <div class="dropdown">
                    <div style="color: black; text-decoration: none; letter-spacing: 1px; font-weight: bold; font-size: 17px"
                         class="dropbtn">Service Centers <i class="fa fa-arrow-down" aria-hidden="true"></i>
                    </div>
                    <div class="dropdown-content">
                        <a href="#">Colombo</a>
                        <a href="#">Negombo</a>
                        <a href="#">Galle</a>
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
                    </div>
                </div>
            </li>
            <li style="list-style: none">
                <div class="dropdown">
                    <div style="color: black; text-decoration: none; letter-spacing: 1px; font-weight: bold; font-size: 17px"
                         class="dropbtn">Account <i class="fa fa-arrow-down" aria-hidden="true"></i>
                    </div>
                    <div class="dropdown-content">
                        <a href="#">Settings</a>
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
    <ul style="display: flex; width: 30%; justify-content: space-around; margin-top: 10px">
        <li style="list-style: none">
            <a style="color: black; text-decoration: none; letter-spacing: 1px; font-weight: bold; font-size: 17px"
               href="#">Shipments</a>
        </li>
        <li style="list-style: none">
            <a style="color: black; text-decoration: none; letter-spacing: 1px; font-weight: bold; font-size: 17px"
               href="#">Drivers</a>
        </li>
        <li style="list-style: none">
            <div class="dropdown">
                <div style="color: black; text-decoration: none; letter-spacing: 1px; font-weight: bold; font-size: 17px"
                     class="dropbtn">Account <i class="fa fa-arrow-down" aria-hidden="true"></i>
                </div>
                <div class="dropdown-content">
                    <a href="#">Settings</a>
                    <a style="color: red" href="/logout">Log out</a>
                </div>
            </div>
        </li>
    </ul>
    </sec:authorize>
</nav>