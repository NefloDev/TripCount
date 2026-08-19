package neflo.dev.tripcount.database

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object DatabaseClient{

    val supabase = createSupabaseClient(
        supabaseUrl = "127.0.0.1",
        supabaseKey = "123456789"
    ) {
        install(Postgrest)
    }
}