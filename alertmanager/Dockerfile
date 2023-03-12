FROM prom/alertmanager:v0.25.0@sha256:db8303fa05341f5dc6b19b36a97325cd1b8307254ed9042a2c554af71f3c0284

FROM alpine:latest
RUN apk add gettext

COPY --from=0 /bin/alertmanager /bin/alertmanager

ARG SLACK_HOOK_URL
ENV SLACK_HOOK_URL $SLACK_HOOK_URL

COPY alertmanager.yml.template /etc/alertmanager/alertmanager.yml.template

CMD envsubst < /etc/alertmanager/alertmanager.yml.template > /etc/alertmanager/alertmanager.yml && \
    /bin/alertmanager --config.file=/etc/alertmanager/alertmanager.yml