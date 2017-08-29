SUMMARY = "Mono module for Apache web server"
DESCRIPTION = ""
HOMEPAGE = "https://github.com/mono/mod_mono"
SECTION = "mono"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=e23fadd6ceef8c618fc1c65191d846fa"

DEPENDS = "apache2 apache2-native mono"

PV = "3.12"
S = "${WORKDIR}/mod_mono-${PV}"
SRC_URI = "https://github.com/mono/mod_mono/archive/${PV}.tar.gz"
SRC_URI[md5sum] = "c792114b299ec4d022b2ea0c28790e1e"
SRC_URI[sha256sum] = "a0131445630f72a494884db21dd407515543ee6c7906ab08bbcff2c214674d98"

do_configure() {    
    sed -i 's/$LIBTOOL/libtoolize/g' autogen.sh
    set +e
    ${CACHED_CONFIGUREVARS} ${S}/autogen.sh --verbose ${CONFIGUREOPTS} ${EXTRA_OECONF}
    if [ "$?" != "0" ]; then
	echo "Configure failed. The contents of all config.log files follows to aid debugging"
	find ${S} -name config.log -print -exec cat {} \;
	bbfatal "oe_runconf failed"
    fi
    set -e
}

FILES_${PN} += " ${libdir}/apache2/modules/* "