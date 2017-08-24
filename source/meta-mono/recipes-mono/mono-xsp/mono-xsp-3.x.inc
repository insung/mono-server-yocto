DESCRIPTION = "Standalone web server written in C# that can be used to run ASP.NET applications"
SECTION = "devel/mono"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=9e1603e27acd337015fdfa7f7ff936c7"

inherit autotools-brokensep
inherit mono

DEPENDS = "mono mono-native \
                         gettext gettext-minimal-native gettext-native \
						 texinfo-dummy-native \
						 python3-native \
						 flex-native \
						 bison-native \
						 binutils-cross-i586 \
						 libtool \
						 zlib zlib-native \
						 linux-libc-headers gcc-runtime libgcc gcc-cross-i586 \
						 libmpc libmpc-native \
						 gmp gmp-native \
						 mpfr mpfr-native \
						 attr attr-native \
						 pkgconfig-native \
						 glibc \
						 glib-2.0 glib-2.0-native \
						 sqlite3-native \
						 pseudo-native \
						 unifdef-native \
						 elfutils-native \
						 popt-native \
						 rpm-native \
						 libarchive-native \
						 db-native \
						 nss-native \
						 dbus dbus-native \
						 file-native \
						 m4-native \
						 autoconf-native \
						 gperf-native \
						 fontconfig fontconfig-native \
						 freetype freetype-native \
						 libpng libpng-native \
						 libgdiplus libgdiplus-native \
						 pango-native \
						 cairo cairo-native \
						 libxft libxft-native \
						 libjpeg-turbo \
						 giflib giflib-native \
						 expat expat-native \
						 bzip2 bzip2-native \
						 openssl-native \
						 readline-native \
						 e2fsprogs-native \
						 libx11 libx11-native \
						 gobject-introspection-native \
						 harfbuzz-native \
						 libxrender libxrender-native \
						 pixman pixman-native \
						 libxext libxext-native \
						 util-macros util-macros-native \
						 xz xz-native \
						 tiff \
						 nasm-native \
						 pigz-native \
						 libffi libffi-native \
						 util-linux util-linux-native \
						 xextproto xextproto-native \
						 qemu-native \
						 ncurses ncurses-native \
						 lzo-native \
						 makedepend-native \
						 cryptodev-linux-native \
						 gtk-doc-native \
						 libpcre libpcre-native \
						 renderproto renderproto-native \
						 xtrans xtrans-native \
						 libxcb libxcb-native \
						 kbproto kbproto-native \
						 inputproto inputproto-native \
						 libsm libsm-native \
						 nspr-native \
						 alsa-lib-native \
						 dtc-native \
						 groff-native \
						 icu-native \
						 libexif \
						 libpthread-stubs libpthread-stubs-native \
						 libxdmcp libxdmcp-native \
						 libxau-native \
						 libice libice-native \
						 libxrandr-native \
						 libxau \
						 xcb-proto xcb-proto-native \
						 bash-completion \
						 randrproto-native \
						 opkg-utils \
						 base-passwd \
						 shadow shadow-sysroot \
						 base-files \
						 update-rc.d \
						 xproto xproto-native \
						 libsdl-native \
						 initscripts \
						 "

SRC_URI = "https://github.com/mono/xsp/archive/${PV}.tar.gz"

S = "${WORKDIR}/xsp-${PV}"

do_configure () {
	aclocal -I build/m4/shamrock -I build/m4/shave
	libtoolize --force --copy
	autoconf
	automake --gnu --add-missing --force --copy
	sed -i 's/@RUNTIME@/\/usr\/bin\/mono/g' scripts/script.in
	./configure --enable-maintainer-mode --prefix=/usr/local
	mv -- '-libtoolT' libtool
	chmod +x libtool
}

PACKAGES += " \
    ${PN}-test \
"

FILES_${PN}-test = "/usr/local/lib/xsp/test/*"
FILES_${PN}-doc += "/usr/local/share/*"
#FILES_${PN}-dbg = "/usr/src/debug/mono-xsp/*"
#FILES_${PN}-dev = ""

INSANE_SKIP_${PN} += "installed-vs-shipped"

# AJL - For now we are going to parcel up the different XSP hosting (mod_mono, fastcgi, xsp) together. More granular packages in future
FILES_${PN} = " \
    /usr/local/bin/* \
    /usr/local/lib/mono/* \
	/usr/local/lib/pkgconfig/* \
	/usr/local/lib/xsp/* \
"

# We seem to run into problems with make parallelisation building mono-xsp, so disable it for now
PARALLEL_MAKE = ""
