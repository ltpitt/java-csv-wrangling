# Java CSV wrangling
> This repo contains how to manually install the key components of CloudPebble as submodules into a Vagrant Linux VM. It also contains a `docker-compose` file that will assemble all of them into something that runs like a real CloudPebble instance. A big thank you from me and the whole Pebble community to the *awesome* Katharine Berry - (<https://twitter.com/KatharineBerry>) for making this possible.  

## Limitations

- Pebble SSO is not available; only local accounts work.
- Websocket installs are not available because pebble SSO is not available

## Prerequisites

- Oracle VirtualBox (how to install: https://www.virtualbox.org/wiki/Downloads)
- Vagrant (how to install: https://www.vagrantup.com/intro/getting-started/install.html)

## How to use

Point your web browser to http://localhost

## Release History

* 0.0.1
    * Implemented all methods / tests required

## Meta

Davide Nastri – [@pitto](https://twitter.com/pitto) – d.nastri@gmail.com

Distributed under the GPL license. See ``LICENSE`` for more information.

[https://github.com/ltpitt/vagrant-cloudpebble-composed](https://github.com/ltpitt/)

## Contributing

1. Fork it (<https://github.com/ltpitt/vagrant-cloudpebble-composed/fork>)
2. Create your feature branch (`git checkout -b feature/fooBar`)
3. Commit your changes (`git commit -am 'Add some fooBar'`)
4. Push to the branch (`git push origin feature/fooBar`)
5. Create a new Pull Request

<!-- Markdown link & img dfn's -->
[npm-image]: https://img.shields.io/npm/v/datadog-metrics.svg?style=flat-square
[npm-url]: https://npmjs.org/package/datadog-metrics
[npm-downloads]: https://img.shields.io/npm/dm/datadog-metrics.svg?style=flat-square
[travis-image]: https://img.shields.io/travis/dbader/node-datadog-metrics/master.svg?style=flat-square
[travis-url]: https://travis-ci.org/dbader/node-datadog-metrics
[wiki]: https://github.com/yourname/yourproject/wiki
