# build
custom_build(
    ref = 'accounting-organization',
    command = './gradlew bootBuildImage --imageName $EXPECTED_REF',
    deps = ['build.gradle', 'src']
)

# deploy
k8s_yaml(['k8s/deployment.yml', 'k8s/service.yml'])

# manage
k8s_resource('accounting-organization', port_forwards=['8000'])