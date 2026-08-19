package neflo.dev.tripcount.database

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object DatabaseClient{

    val systemEnv: Map<String, String> = System.getenv()

    val supabase = createSupabaseClient(
        supabaseUrl = systemEnv.getValue("DB_URL"),
        supabaseKey = systemEnv.getValue("DB_PASSWORD")
    ) {
        install(Postgrest)
    }
}