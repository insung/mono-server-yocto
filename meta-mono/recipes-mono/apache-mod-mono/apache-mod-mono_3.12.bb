SUMMARY = "Mono module for Apache web server"
DESCRIPTION = ""
HOMEPAGE = "https://github.com/mono/mod_mono"
SECTION = "mono"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=e23fadd6ceef8c618fc1c65191d846fa"

DEPENDS = "apache2 apache2-native libtool"

PV = "3.12"
S = "${WORKDIR}/mod_mono-${PV}"
SRC_URI = "https://github.com/mono/mod_mono/archive/${PV}.tar.gz"
SRC_URI[md5sum] = "c792114b299ec4d022b2ea0c28790e1e"
SRC_URI[sha256sum] = "a0131445630f72a494884db21dd407515543ee6c7906ab08bbcff2c214674d98"

do_configure () { 
    aclocal
    autoheader
    libtoolize --force --copy
    automake -a --include-deps
    autoconf
    sed -i 's/${host_alias}-libtool/${host_alias}libtool/g' configure
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