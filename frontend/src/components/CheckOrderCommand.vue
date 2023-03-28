<template>

    <v-card outlined>
        <v-card-title>
            CheckOrder
        </v-card-title>

        <v-card-text>
            <Boolean label="CheckOrder" v-model="value.checkOrder" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="checkOrder"
            >
                CheckOrder
            </v-btn>
            
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'CheckOrderCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.checkOrder = false;
        },
        watch: {
        },
        methods: {
            checkOrder() {
                this.$emit('checkOrder', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

