require recipes-sato/images/core-image-sato.bb

require core-image-mono.inc

# Build up complete meta-mono test image here
IMAGE_INSTALL += " \          
		  dbus-sharp \
		  dbus-sharp-glib \ 
		  gtk-sharp \
		  mono-helloworld \
		  mono-upnp \
		  mono-xsp \
		  monotools-server \
		  taglib-sharp \
		  fsharp \
		  libgdiplus \ 
		  mono-basic \
"

IMAGE_BASENAME = "${PN}"

