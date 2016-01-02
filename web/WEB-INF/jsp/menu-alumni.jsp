<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <div class="nav-collapse" >
                <div class="pull-left">
                    <a href="index.htm" class="brand"><span class="text-warning"><fmt:message key="title"/></span></a>
                </div>
                <ul class="nav pull-left">
                    <li class="divider-vertical"></li>
                    <li><a href="alumni-main.htm">Beranda</a></li>
                    <li><a href="alumni-profil.htm">Profil</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Pengelolaan Data</a>
                        <ul class="dropdown-menu">
                            <li><a href="alumni-studi.htm">Studi</a></li>
                            <li><a href="alumni-kerja.htm">Kerja</a></li>
                            <li><a href="alumni-wirausaha.htm">Wirausaha</a></li>
                        </ul>
                    </li>
                    <li><a href="alumni-kontak.htm">Kontak</a></li>
                </ul>
                <ul class="nav pull-right">
                    <li class="divider-vertical"></li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" href="#" data-toggle="dropdown"><strong class="icon-user"></strong> <%=session.getAttribute("username")%></a>
                        <ul class="dropdown-menu">
                            <li><a href="ganti-password.htm">Ganti Password</a></li>
                            <li><a href="logout.htm">Keluar</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>