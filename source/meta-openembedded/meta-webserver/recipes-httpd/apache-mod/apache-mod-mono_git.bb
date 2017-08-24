SUMMARY = "Mono module for Apache web server"
DESCRIPTION = ""
HOMEPAGE = "https://github.com/mono/mod_mono"
SECTION = "net"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=e23fadd6ceef8c618fc1c65191d846fa"

DEPENDS = "apache2 apache2-native pbzip2-native autoconf automake libtool-cross"
RDEPENDS_${PN} += "apache2"
RRECOMMENDS_${PN} = "libtool"

SRC_URI = "git://github.com/mono/mod_mono.git"
SRCREV = "f21ce5a86a610aba053042324970706a9c424681"

PV = "0.1.1"

S = "${WORKDIR}/git"

do_configure() {    
    aclocal
    autoheader
    libtool --force --install
    automake -a
    autoconf
    ./configure
}

do_compile() {
    make
}

do_install() {
    install -d ${D}${libdir}/apache2/modules/
    install ${B}/src/.libs/mod_mono.so ${D}${libdir}/apache2/modules/
}

FILES_${PN} += " ${libdir}/apache2/modules/* "
FILES_${PN}-dbg += " ${libdir}/apache2/modules/.debug/* "
