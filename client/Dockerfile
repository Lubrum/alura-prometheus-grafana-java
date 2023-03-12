FROM debian:latest@sha256:6f1f698e20c8ae73b110eb614a2c6d13fbdb504bbbbcf2965c4d0b80b7bb7fb1

USER root

COPY ./client.sh /scripts/client.sh

RUN apt update && \
        apt install curl -y && \
        chmod +x /scripts/client.sh

ENTRYPOINT ["/scripts/client.sh"]
