# build
custom_build(
    ref = 'accounting-organization',
    command = './gradlew bootBuildImage --imageName $EXPECTED_REF',
    deps = ['build.gradle', 'src']
)

# db
k8s_yaml(['k8s/postgres.yml'])
k8s_resource('accounting-organization-db', port_forwards=['5432:5432'])

# deploy app
k8s_yaml(['k8s/deployment.yml', 'k8s/service.yml'])

# manage
k8s_resource('accounting-organization', resource_deps=['accounting-organization-db'])
k8s_resource('accounting-organization', port_forwards=['8000'])