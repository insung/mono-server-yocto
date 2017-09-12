
## Introduction

This source cloned openembedded meta-mono layer.  
This source supports mono-xsp recipe 3.0.11 - 4.4 version.  

See below for more details.  

* meta-mono information: 
  * https://layers.openembedded.org/layerindex/branch/master/layer/meta-mono/  

* git repo: 
  * git://git.yoctoproject.org/meta-mono  
  * https://git.yoctoproject.org/git/meta-mono  

## Build Examples

git clone lastest yocto project (poky-pyro)
```
git clone -b pyro git://git.yoctoproject.org/poky.git {your-poky-home}
```

git clone meta-openembedded and move meta-oe directory
```
git clone git://git.openembedded.org/meta-openembedded
mv meta-openembedded/meta-oe {your-poky-home}
```

git clone this source
```
sudo apt-get install mono-complete
cd {your-poky-home}
git clone https://github.com/insung/meta-mono.git
```

set env
```
source oe-init-build-env
```

modify bblayers.conf
```
vi conf/bblayers.conf

# add below lines to BBLAYERS ?=
${YOCTOROOT}/meta-oe \
${YOCTOROOT}/meta-mono \
```

modify local.conf
```
vi conf/local.conf

# change package rpm to deb
PACKAGE_CLASSES ?= "package_deb"

# install package
IMAGE_INSTALL_append = " mono mono-xsp"

# extra space (optional)
IMAGE_ROOTFS_EXTRA_SPACE = "4194304"

# specific mono-xsp version
PREFERRED_VERSION_mono-native = "4.6.2.16"
PREFERRED_VERSION_mono = "4.6.2.16"
PREFERRED_VERSION_mono-xsp = "4.4"
```

bitbake mono image recipe
```
bitbake image-full-mono
```

bitbake clean mono image recipe
```
bitbake -c cleanall image-full-mono
```


## Copyright
  
MIT/GPLv2 - following the lead of libgdiplus and mono
