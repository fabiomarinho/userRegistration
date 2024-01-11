
docker run --rm \
    -v ./openApi:/local openapitools/openapi-generator-cli generate \
    -i /local/api.yaml \
    -g spring \
    -o /local/out \
    --artifact-id userRegistration --group-id com.pnc