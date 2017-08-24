SUMMARY = "Mono module for Apache web server"
DESCRIPTION = ""
HOMEPAGE = "https://github.com/mono/mod_mono"
SECTION = "net"
LICENSE = "Apache-2.0"

PV = "0.1"

DEPENDS = "apache2 apache2-native pbzip2-native autoconf automake libtool"
RDEPENDS_${PN} += "apache2"
# RRECOMMENDS_${PN} = "libtool"

# original mod_mono source from github
S = "${WORKDIR}/git"
LIC_FILES_CHKSUM = "file://COPYING;md5=e23fadd6ceef8c618fc1c65191d846fa"
SRC_URI = "git://github.com/mono/mod_mono.git"
SRCREV = "f21ce5a86a610aba053042324970706a9c424681"

# customized mod_mono source
#S = "${WORKDIR}/mod_mono"
#LIC_FILES_CHKSUM = "file://COPYING;md5=e23fadd6ceef8c618fc1c65191d846fa"
#SRC_URI = "file://mod_mono-${PV}.tar.gz"
#SRCREV = "e5088ef46f8fa3190bfb2c78352fecd1"

#EXTRA_OECONF = "APACHECTL=${STAGING_DIR_TARGET}${sbindir}/apachectl \
#                LIBTOOL=${STAGING_DIR_TARGET}${bindir_crossscripts}/${HOST_SYS}-libtool"

do_configure() {    
    aclocal
    autoheader
    libtoolize --force --copy
    #libtoolize --force --automake
    automake -a --include-deps
    #automake -a --include-deps --force --add-missing
    autoconf
    #autoreconf --install --force
    #./configure --prefix=${S}
    ./configure
    mv -- '-libtoolT' libtool
    chmod +x libtool    
    #./scripts/patch-quiet.sh ./Makefile
    #cp ${TOPDIR}/../src/mod_mono/autogen.sh ${WORKDIR}/mod_mono
    #./autogen.sh
}

do_compile() {
    #ln -s /usr/bin/libtoolize /usr/bin/libtool
    make
}

do_install() {
    install -d ${D}${libdir}/apache2/modules/
    install ${B}/src/.libs/mod_mono.so ${D}${libdir}/apache2/modules/
    #oe_runmake install
}

FILES_${PN} += " ${libdir}/apache2/modules/* "
#FILES_${PN}-dbg += " ${libdir}/apache2/modules/.debug/* "