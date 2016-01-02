<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <div class="nav-collapse" >
                <div class="pull-left">
                    <a href="index.htm" class="brand"><span class="text-warning"><fmt:message key="title"/></span></a>
                </div>
                <ul class="nav pull-left">
                    <li class="divider-vertical"></li>
                    <li><a href="pegawai-main.htm">Beranda</a></li>
                    <li><a href="pegawai-profil.htm">Profil</a></li>
                    <li><a href="sms-broadcast.htm">Broadcast Pesan</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Pengelolaan Data Alumni</a>
                        <ul class="dropdown-menu">
                            <li><a href="alumni-upload.htm">Import Data</a></li>
                            <!--li><a href="alumni-simpan.htm">Masukkan Data</a></li>
                            <li><a href="alumni-display.htm">Tampilkan Data</a></li-->
                            <li class="dropdown-submenu">
                                <a tabindex="-1" href="#" id="sub-user">Tampilkan Data</a>
                                <ul class="dropdown-menu" id="submenu-user">
                                    <li><a href="alumni-display.htm">Data Yudisium</a></li>
                                    <li><a href="studi-display.htm">Data Studi Lanjut</a></li>
                                    <li><a href="kerja-display.htm">Data Kerja</a></li>
                                    <li><a href="wirausaha-display.htm">Data Wirausaha</a></li>
                                    <li><a href="user-alumni.htm">Data User Alumni</a></li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    <!--li><a href="laporan-chart.htm">Laporan</a></li-->
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Laporan</a>
                        <ul class="dropdown-menu">
                            <li><a href="laporan-core.htm">Core Non-Core</a></li>
                            <li><a href="laporan-masa-tunggu.htm">Masa Tunggu</a></li>
                            <li><a href="laporan-kontak.htm">Kontak Alumni</a></li>
                            <li><a href="laporan-employment-rate.htm">Employment Rate</a></li>
                            <li class="dropdown-submenu">
                                <a tabindex="-1" href="#" id="sub-posisi-alumni">Posisi Alumni</a>
                                <ul class="dropdown-menu" id="submenu-posisi-alumni">
                                    <li><a href="laporan-posisi-total.htm">Total Alumni</a></li>
                                    <li><a href="laporan-posisi-ipk-le.htm">IPK < 2.75</a></li>
                                    <li><a href="laporan-posisi-ipk-gr.htm">IPK >= 2.75</a></li>
                                </ul>
                            </li>
                            <li><a href="rekapitulasi-grafik.htm">Grafik Posisi</a></li>
                        </ul>
                    </li>
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