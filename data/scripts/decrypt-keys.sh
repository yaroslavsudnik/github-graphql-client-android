#!/bin/bash
if [ -z "$1" ]; then
    echo 'You must provide secret key as first param' >&2
    exit 2
else
    openssl aes-256-cbc -d -md sha256 -nosalt -a -pass pass:$1 -in data/src/main/resources/keys.properties.crypted -out data/src/main/resources/keys.properties
fi
