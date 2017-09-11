# mono-server-yocto
bitbake apache2 + mono + libapache2-mod-mono server using yocto system

# Usage  

1. install mono complete package
```bash
$ sudo apt-get install mono-complete libapache2-mod-mono apache2
```
2. modify IMAGE_INSTALL_append = **" mono mono-xsp"**
```bash
$ vi {build-home}/conf/local.conf
```
3. bikbake for apache2 + mono
```bash
$ source oe-init-build-env
$ bitbake core-image-full-cmdline
```
4. or bitbake full mono features
```bash
$ bitbake image-full-mono
```
5. bitbake libapache2-mod-mono
```bash
$ bitbake apache-mod-mono
```

# Version

* mono : 4.6.2.16
* mono-xsp : 4.4
* mod_mono : 3.12

# References

#### [sources]  
yocto layer index : https://layers.openembedded.org/layerindex/branch/master/layers/
meta-mono : https://github.com/DynamicDevices/meta-mono
meta-openembedded : https://github.com/openembedded/meta-openembedded

mono source : https://github.com/mono/mono
mod_mono : https://github.com/mono/mod_mono
mono fastcgi server : https://github.com/xplicit/HyperFastCgi
apache websocket : https://github.com/jchampio/apache-websocket

#### [mod_mono doc]  
mod_mono : http://www.mono-project.com/docs/web/mod_mono/
