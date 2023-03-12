FROM prom/prometheus:v2.42.0@sha256:5689a4360cf1479bbad944b1311a90b495546c498c46f48e44fa7e5ac6412191

FROM alpine:latest
RUN apk add gettext

COPY --from=0 /bin/prometheus /bin/prometheus

ARG PROMETHEUS_USER
ENV PROMETHEUS_USER $PROMETHEUS_USER

RUN mkdir -p /prometheus /etc/prometheus
RUN chown -R $PROMETHEUS_USER:$PROMETHEUS_USER /etc/prometheus /prometheus

RUN echo $'#!/bin/sh\n\
envsubst < /etc/prometheus/prometheus.template.yml > /etc/prometheus/prometheus.yml && \
exec /bin/prometheus "$@"' \
> /etc/prometheus/entrypoint.sh

RUN chmod +x /etc/prometheus/entrypoint.sh

ENTRYPOINT ["/etc/prometheus/entrypoint.sh"]
CMD [ "--config.file=/etc/prometheus/prometheus.yml", "--storage.tsdb.path=/prometheus" ]

USER $PROMETHEUS_USER
VOLUME [ "/prometheus" ]
WORKDIR /prometheus

ADD prometheus.yml /etc/prometheus/prometheus.template.yml